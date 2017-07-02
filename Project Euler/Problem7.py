def isPrime(num):
	for x in range(2, num):
		if num % x == 0:
			return False
	return True

numPrime = 10001; count = 0; num = 2
while count < numPrime:
	if(isPrime(num)):
		count += 1
	num += 1

print(num - 1)
