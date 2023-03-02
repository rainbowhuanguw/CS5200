import csv


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


def clean(filename):
    total = rawcount(filename)
    with open(filename, 'r') as infile, open('cleaned_'+filename, 'w',newline='') as outfile:
        reader = csv.reader(infile)
        writer = csv.writer(outfile)
        cleaned_count = 0
        count = 0
        for row in reader:
            new_row = []
            for item in row:
                if '\n' in item or '\\' in item:
                    new_item = item.replace('\n', '|').replace('\\', '|')
                    cleaned_count += 1
                else:
                    new_item = item
                new_row.append(new_item)
            count += 1
            writer.writerow(new_row)
            print("Cleaning {}/{} records...".format(count, total), end="\r")
    print()
    print("Cleaned {}/{} records".format(cleaned_count, count))


if __name__ == "__main__":
    clean("airbnb_listings_usa.csv")
