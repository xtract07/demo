# demo
PostMan Commands after running the app
Examples
1. to Add Hotel 

Post  localhost:8080/addHotel
{
  "name" : "demo",
  "weekendRates" : 100,
  "weekdayRates" : 90,
}

2. To find the cheapest Hotel

Get localhost:8080/listCheapest
{
  "fromDate" : "12/07/2022",
  "toDate" : "29/07/2022"
}


3. To list all Hotels

Get localhost:8080/listAllHotels


4. Delete Hotel(pass the name of the hotel in the API itself)

Delete localhost:8080/deleteHotel/{name}
