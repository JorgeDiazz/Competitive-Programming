sumSquares = 0; squareSum = 0
for x in range(1,101):
	sumSquares += x**2
	squareSum += x
squareSum**=2
print(squareSum - sumSquares)
