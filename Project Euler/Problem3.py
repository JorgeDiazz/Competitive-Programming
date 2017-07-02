def factors(num):
	limit = num >> 20; largestFactor = 0
	for x in range(2, limit):
		if isPrime(x) and num % x == 0:
			largestFactor = x
	return largestFactor

def isPrime(num):
	for x in range(2, num):
		if num % x == 0:
			return False
	return True

print(factors(600851475143))


