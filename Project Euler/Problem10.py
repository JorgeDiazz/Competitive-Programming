def isPrime(num):
    limit = int(num ** .5) + 2
    for x in range(2, limit):
        if not num % x:
            return False
    return True

limit = 2000000; sum = 17        
for x in range(11, limit):
    if isPrime(x):
        sum += x
    x += 1
print(sum)
