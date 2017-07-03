matrix = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []]

for x in range(0,20): matrix[x] = input().split()

result = 0; currentResult = 1

#Left to Right
for row in matrix:
    for start in range(17):
        for index in range(start, start + 4):
            currentResult *= int(row[index])
        result = max(currentResult, result)
        currentResult = 1

#Up to Down
for column in range(20):
    for row in range(17):
        for rowIterate in range(row, row + 4):
            currentResult *= int(matrix[rowIterate][column])
        result = max(currentResult, result)
        currentResult = 1        

#Left to Rigth Diagonal
for row in range(17):
    for index in range(17):
        currentResult *= int(matrix[row][index]) * int(matrix[row + 1][index + 1]) * int(matrix[row + 2][index + 2]) * int(matrix[row + 3][index + 3])
        result = max(currentResult, result)
        currentResult = 1                

#Rigth to Left Diagonal
column = 19; row = 0
while column > 2:
    row = 0
    while row < 17:
        currentResult *= int(matrix[row][column]) * int(matrix[row + 1][column - 1]) * int(matrix[row + 2][column - 2]) * int(matrix[row + 3][column - 3])
        result = max(currentResult, result)
        currentResult = 1
        row += 1                
    column -= 1    
    
print(result)
