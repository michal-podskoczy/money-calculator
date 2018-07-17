#Add expense

#curl -H "Content-Type: application/json" -X POST -d '{"category" : "FOOD","date" : "2018-03-10","amount" : 1135.00,"balanceCurrency" : "PLN"}' http://localhost:8080/expenses

#Get all expenses
curl -get http://localhost:8080/expenses