import json
import pandas as pd
import csv
import gc
from timeit import default_timer as timer


def rawcount(filename):
    f = open(filename, 'rb')
    lines = 0
    buf_size = 1024 * 1024
    read_f = f.raw.read

    buf = read_f(buf_size)
    while buf:
        lines += buf.count(b'\n')
        buf = read_f(buf_size)

    return lines

def outputDataFrames(df_list: list, path: str, first: bool) -> None:
    df = pd.concat(df_list, ignore_index=True, sort=False)

    if first:
        df.to_csv(path, index=False)
    else:
        df.to_csv(path, mode='a', index=False, header=False)


def handleBusiness(path: str, partition_size: int = 50000) -> dict:
    data_frames = [[] for _ in range(4)]
    total = rawcount(path)
    with open(path, 'r') as f:
        count = 0
        for line in f:
            data = json.loads(line)
            df = pd.json_normalize(data)

            categories = df[['business_id', 'categories']]

            attributes = None
            if data['attributes']:
                attributes = pd.json_normalize(data['attributes'])
                attributes['business_id'] = df['business_id']

            hours_col = ['business_id', 'hours.Monday', 'hours.Tuesday', 'hours.Wednesday',
                         'hours.Thursday', 'hours.Friday', 'hours.Saturday', 'hours.Sunnday']

            hours = df.reindex(hours_col, axis=1)
            hours.columns = hours.columns.map(lambda x: x.split(".")[-1])

            df = df[['business_id', 'name', 'address', 'city', 'state', 'postal_code',
                    'latitude', 'longitude', 'stars', 'review_count', 'is_open']]

            data_frames[0].append(df)
            data_frames[1].append(categories)
            data_frames[2].append(attributes)
            data_frames[3].append(hours)
            # if count == 100:
            #     break
            count += 1
            print("Converting {}/{} records...".format(count,total), end="\r")
            if count % partition_size==0:
                outputDataFrames(data_frames[0], 'business.csv', first=count == partition_size)
                outputDataFrames(data_frames[1], 'category.csv', first=count == partition_size)
                outputDataFrames(data_frames[2], 'attributes.csv', first=count == partition_size)
                outputDataFrames(data_frames[3], 'hours.csv', first=count == partition_size)
                data_frames = [[] for _ in range(4)]
                gc.collect()
        if count % partition_size != 0:
            outputDataFrames(data_frames[0], 'business.csv', first=count < partition_size)
            outputDataFrames(data_frames[1], 'category.csv', first=count < partition_size)
            outputDataFrames(data_frames[2], 'attributes.csv', first=count < partition_size)
            outputDataFrames(data_frames[3], 'hours.csv', first=count < partition_size)
        print("Converted {} business records".format(count))


def handleReview(path: str, partition_size: int = 50000) -> dict:
    data_frames = []
    total = rawcount(path)
    with open(path, 'r') as f:
        count = 0
        for line in f:
            data = json.loads(line)
            df = pd.json_normalize(data)

            df = df[['review_id', 'user_id', 'business_id', 'stars', 'useful', 'funny',
                    'cool', 'text', 'date']]
            df['text']=df['text'].str.replace("\n","\\n")

            data_frames.append(df)
            # if count == 100:
            #     break
            count += 1
            print("Converting {}/{} records...".format(count,total), end="\r")
            if count % partition_size==0:
                outputDataFrames(data_frames, 'review.csv', first=count == partition_size)
                data_frames = []
                gc.collect()
        if count % partition_size != 0:
            outputDataFrames(data_frames, 'review.csv', first=count < partition_size)
        print("Converted {} review records".format(count))


def handleUsers(path: str, partition_size: int = 50000) -> dict:
    data_frames = []
    total = rawcount(path)
    with open(path, 'r') as f:
        count = 0
        for line in f:
            # if count == 1:
            #     print(line)
            data = json.loads(line)
            df = pd.json_normalize(data)

            df = df[["user_id", "name", "review_count", "yelping_since"
                ,"useful","funny","cool","fans","average_stars","compliment_hot"
                ,"compliment_more","compliment_profile","compliment_cute","compliment_list"
                ,"compliment_note","compliment_plain","compliment_cool","compliment_funny"
                ,"compliment_writer","compliment_photos"]]

            data_frames.append(df)
            if count == 10000:
                break
            count += 1
            print("Converting {}/{} records...".format(count,total), end="\r")
            if count % partition_size==0:
                outputDataFrames(data_frames, 'users.csv', first=count == partition_size)
                data_frames = []
                gc.collect()
        if count % partition_size != 0:
            outputDataFrames(data_frames, 'users.csv', first=count < partition_size)
        print("Converted {} users records".format(count))       

if __name__ == "__main__":
    start = timer()
    # handleBusiness("./yelp_academic_dataset_business.json")
    handleUsers("C:/Users/youyu/OneDrive - Northeastern University/CS 5200 Database Management/yelp_academic_dataset_user.json")
    print(f"Finished in {timer()-start}s")
