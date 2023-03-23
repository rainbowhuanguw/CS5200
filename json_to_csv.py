import json
import gc
import pandas as pd
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
            df = pd.json_normalize(data, max_level=0)
            categories = df[['business_id', 'categories']]

            attributes = df[['business_id', 'attributes']]

            hours_col = ['business_id', 'Monday', 'Tuesday', 'Wednesday',
                         'Thursday', 'Friday', 'Saturday', 'Sunday']

            # hours = df.reindex(hours_col, axis=1)
            hours = data['hours'] if data['hours'] else {}
            hours['business_id'] = data['business_id']
            for k in hours_col:
                if k not in hours:
                    hours[k] = None
            hours = pd.json_normalize(hours)

            df = df[['business_id', 'name', 'address', 'city', 'state', 'postal_code',
                    'latitude', 'longitude', 'stars', 'review_count', 'is_open']]

            data_frames[0].append(df)
            data_frames[1].append(categories)
            data_frames[2].append(attributes)
            data_frames[3].append(hours)
            # if count == 100:
            #     break
            count += 1
            print("Converting {}/{} records...".format(count, total), end="\r")
            if count % partition_size == 0:
                outputDataFrames(
                    data_frames[0], 'business.csv', first=count == partition_size)
                outputDataFrames(
                    data_frames[1], 'category.csv', first=count == partition_size)
                outputDataFrames(
                    data_frames[2], 'attributes.csv', first=count == partition_size)
                outputDataFrames(
                    data_frames[3], 'hours.csv', first=count == partition_size)
                data_frames = [[] for _ in range(4)]
                gc.collect()
        if count % partition_size != 0:
            outputDataFrames(
                data_frames[0], 'business.csv', first=count < partition_size)
            outputDataFrames(
                data_frames[1], 'category.csv', first=count < partition_size)
            outputDataFrames(
                data_frames[2], 'attributes.csv', first=count < partition_size)
            outputDataFrames(
                data_frames[3], 'hours.csv', first=count < partition_size)
        print()
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
            df['text'] = df['text'].str.replace("\n", "\\n")
            df['text'] = df['text'].str.replace("\r", "\\r")

            data_frames.append(df)
            # if count == 100:
            #     break
            count += 1
            print("Converting {}/{} records...".format(count, total), end="\r")
            if count % partition_size == 0:
                outputDataFrames(data_frames, 'review.csv',
                                 first=count == partition_size)
                data_frames = []
                gc.collect()
            if count == 10000:
                break
        if count % partition_size != 0:
            outputDataFrames(data_frames, 'review.csv',
                             first=count < partition_size)
        print()
        print("Converted {} review records".format(count))


def handleTip(path: str, partition_size: int = 50000) -> dict:
    data_frames = []
    total = rawcount(path)
    with open(path, 'r') as f:
        count = 0
        for line in f:
            data = json.loads(line)
            df = pd.json_normalize(data)

            df = df[['user_id', 'business_id', 'compliment_count',
                    'date', 'text']]
            df['text'] = df['text'].str.replace("\n", "\\n")
            df['text'] = df['text'].str.replace("\r", "\\r")

            data_frames.append(df)
            if count == 20000:
                break
            count += 1
            print("Converting {}/{} records...".format(count, total), end="\r")
            if count % partition_size == 0:
                outputDataFrames(data_frames, 'tip.csv',
                                 first=count == partition_size)
                data_frames = []
                gc.collect()
        if count % partition_size != 0:
            outputDataFrames(data_frames, 'tip.csv',
                             first=count < partition_size)
        print()
        print("Converted {} tip records".format(count))

        
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
            df['text'] = df['text'].str.replace("\n", "\\n")
            df['text'] = df['text'].str.replace("\r", "\\r")

            data_frames.append(df)
            # if count == 100:
            #     break
            count += 1
            print("Converting {}/{} records...".format(count, total), end="\r")
            if count % partition_size == 0:
                outputDataFrames(data_frames, 'review.csv',
                                 first=count == partition_size)
                data_frames = []
                gc.collect()
            if count == 10000:
                break
        if count % partition_size != 0:
            outputDataFrames(data_frames, 'review.csv',
                             first=count < partition_size)
        print()
        print("Converted {} review records".format(count))

def handelUsers(path: str, partition_size: int = 50000) -> dict:
    data_frames = []
    total = rawcount(path)
    with open(path,'r') as f:
        count = 0
        for line in f:
            data = json.loads(line)
            df = pd.json_normalize(data)

            df = df[["user_id","name","review_count","yelping_since","useful","funny",
                     "cool","fans","average_stars","compliment_hot","compliment_more",
                     "compliment_profile","compliment_cute","compliment_list",
                     "compliment_note","compliment_plain","compliment_cool",
                     "compliment_funny","compliment_writer","compliment_photos"]]

            data_frames.append(df)
            count += 1
            print("Converting {}/{} records...".format(count, total), end="\r")
            if count % partition_size == 0:
                outputDataFrames(data_frames, 'users.csv',
                                 first=count == partition_size)
                data_frames = []
                gc.collect()
            if count == 20000:
                break
        if count % partition_size != 0:
            outputDataFrames(data_frames, 'users.csv',
                             first=count < partition_size)
        print()
        print("Converted {} user records".format(count))


if __name__ == "__main__":
    start = timer()
    # print(rawcount("review.csv"))
    # handleBusiness("./yelp_academic_dataset_business.json")
    # handleReview("./yelp_academic_dataset_review.json")
    # handleTip("./yelp_academic_dataset_tip.json")
    handelUsers("./yelp_academic_dataset_user.json")
    print(f"Finished in {timer()-start}s")
