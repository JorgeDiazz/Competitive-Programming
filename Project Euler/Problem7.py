def isPrime(num):
	for x in range(2, num):
		if num % x == 0:
			return False
	return True

numPrime = 10001; count = 0; num = 1
while count < numPrime:
	num += 1
	if(isPrime(num)):
		count += 1

print(num)
