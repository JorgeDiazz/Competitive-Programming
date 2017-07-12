number = 0
largest_count = 0

for n in range(1, 1000000, 2):
    count = 0; possible = n
    while n != 1:
        n = 3*n + 1 if n % 2 else n >> 1
        count += 1
    if count > largest_count:
        largest_count = count
        number = possible

print(number)
