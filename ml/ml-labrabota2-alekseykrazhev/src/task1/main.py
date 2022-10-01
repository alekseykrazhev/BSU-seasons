
if __name__ == '__main__':
    bread_full_price = 3.52
    sale_cof = 0.45

    yesterday_bread = int(input("Enter amount of yesterday's bread you bought:"))

    print("*"+"Usual bread price: %5.2f".center(50) % bread_full_price + "*")
    print("*"+"Price for yesterday's bread: %5.2f".center(50) % (sale_cof * bread_full_price)+"*")
    print("*"+"Full price: %5.2f".center(50) % (bread_full_price * sale_cof * yesterday_bread)+"*")
