def isPalindrome(num):
	start = 0; end = len(str(num)) - 1
	stringNum = str(num)
	while start < end:
		if stringNum[start] != stringNum[end]:
			return False
		start += 1; end -= 1
	return True

result = 0
for x in range(1,1000):
	for y in range(1,1000):
		prod = x*y
		if prod > result and isPalindrome(prod):
			result = prod
print(result)


