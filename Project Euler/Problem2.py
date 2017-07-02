def fibo(limit):
	first = 1; second = 1; nextNum = 1; sum = 0
	while (nextNum < limit):
		nextNum = first + second
		first = second
		second = nextNum
		if nextNum % 2 == 0:
			sum += nextNum
			print(sum)
	return sum

print(fibo(4000000))

