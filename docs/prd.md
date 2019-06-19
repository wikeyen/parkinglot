# Parking-lot

##Requirements

###2019/06/15
####Description
With one car and one parking-lot, how do you park your car?
####Tasking
>given one car, one not full parking-lot<br>
>when parking<br>
>then parking success and returning a valid ticket related to the car

>given one car, one full parking-lot<br>
>when parking<br>
>then parking failed

>given a parked car, a valid ticket related to the car<br>
>when picking up the car<br>
>then picking up success and destroying the ticket

>given a parked car, a invalid ticket<br>
>when picking up the car<br>
>then picking up failed

>given any parked car, an used ticket<br>
>when picking up a car<br>
>then picking up failed

>given any parked car, no ticket<br>
>when picking up a car<br>
>then picking up failed

###2019/06/17
####Description
A graduate car parking attendant, multiple parking-lots. When a parking-lot is full, then should go to the next one to park (the parking lots are in a sequence) 

####Tasking
>given a graduate parking attendant, one parking boy, one car, two not fully occupied parking-lot<br>
>when parking<br>
>then succeed parking into the first parking-lot, and get a ticket from it<br>
>
>given a graduate parking attendant, one car, two parking-lots with the first one fully occupied and the second one with some vacancies<br>
>when parking<br>
>then parking into the second parking-lot, and get a ticket from it<br>
>
>given a graduate parking attendant, one car, two fully occupied parking-lot<br>
>when parking<br>
>then parking failed<br>
>
>given a graduate parking attendant, one car, no parking-lot<br>
>when parking<br>
>then parking failed<br>
>
>given no car<br>
>when parking<br>
>then parking failed<br>
>
>given a graduate parking attendant, a certain parking-lot with a parked car, and a valid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car success, the ticket should turn to invalid<br>
>
>given a graduate parking attendant, a certain parking-lot with a parked car, and a valid ticket relate to it<br>
>when picking up the car from a wrong parking-lot<br>
>then picking up car failed, the ticket should still be valid<br>
>
>given an invalid ticket<br>
>when picking up a car<br>
>then picking up failed<br>

###2019/06/19
####Description
A smart car parking attendant, multiple parking-lots. He/she should park the car into the parking-lot with most vacancies.

####Tasking
>given a smart parking attendant, two not full parking-lots with the first one more occupied,
>when parking
>then succeed parking into the second parking-lot, and get a ticket form it<br>
>
>given a smart parking attendant, two not full parking-lots with both evenly occupied,
>when parking
>then succeed parking into the first parking-lot, and get a ticket form it<br>