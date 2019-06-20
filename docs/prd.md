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
>given a graduate parking attendant, one parking boy, one car, two not fully occupied parking-lots<br>
>when parking<br>
>then succeed parking into the first parking-lot, and get a ticket from it<br>

>given a graduate parking attendant, one car, two parking-lots with the first one fully occupied and the second one with some vacancies<br>
>when parking<br>
>then parking into the second parking-lot, and get a ticket from it<br>

>given a graduate parking attendant, one car, two fully occupied parking-lots<br>
>when parking<br>
>then parking failed<br>

>given a graduate parking attendant, one car, no parking-lot<br>
>when parking<br>
>then parking failed<br>

>given no car<br>
>when parking<br>
>then parking failed<br>

>given a graduate parking attendant, a certain parking-lot with a parked car, and a valid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car success, the ticket should turn to invalid<br>

>given a graduate parking attendant, a certain parking-lot with a parked car, and an invalid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car failed<br>

>given an invalid ticket<br>
>when picking up a car<br>
>then picking up failed<br>

###2019/06/19
####Description
A smart car parking attendant, multiple parking-lots. He/she should park the car into the parking-lot with most vacancies.
if there were two or more with the same amount of vacancies, then park into the first parking-lot

####Tasking
>given a smart parking attendant, two not full parking-lots with the first one more occupied,
>when parking
>then succeed parking into the second parking-lot, and get a ticket form it<br>

>given a smart parking attendant, two not full parking-lots with both evenly occupied,
>when parking
>then succeed parking into the first parking-lot, and get a ticket form it<br>

>given a smart parking attendant, a certain parking-lot with a parked car, and a valid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car success, the ticket should turn to invalid<br>

>given a smart parking attendant, a certain parking-lot with a parked car, and an invalid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car failed<br>

###2019/06/20
####Description
A super car parking attendant, multiple parking-lots, He/she should park the car into the parking-lot with the lowest occupation rate.
if there were two or more at the same lowest occupation rate, then park into the first parking-lot

####Tasking
>given a super parking attendant, two not full parking-lots with the first one having a higher occupation rate,
>when parking
>then succeed parking into the second parking-lot, and get a ticket form it<br>

>given a super parking attendant, two not full parking-lots with both evenly occupied,
>when parking
>then succeed parking into the first parking-lot, and get a ticket form it<br>

>given a super parking attendant, a certain parking-lot with a parked car, and a valid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car success, the ticket should turn to invalid<br>

>given a super parking attendant, a certain parking-lot with a parked car, and an invalid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car failed<br>

###2019/06/20
####Description
A car parking manager, multiple parking-lots, He/she can park himself/herself while can also ask the graduate, smart and super parking attendant to park.
If he/she is parking himself/herself, then parking into the first available parking-lot

####Tasking
>given a parking car manager, one car, two not fully occupied parking-lots<br>
>when parking<br>
>then succeed parking into the first parking-lot, and get a ticket from it<br>

>given a parking car manager, one car, two parking-lots with the first one fully occupied and the second one with some vacancies<br>
>when parking<br>
>then parking into the second parking-lot, and get a ticket from it<br>

>given a parking car manager, one car, two fully occupied parking-lots<br>
>when parking<br>
>then parking failed<br>

>given a parking car manager, one car, no parking-lot<br>
>when parking<br>
>then parking failed<br>

>given no car<br>
>when parking<br>
>then parking failed<br>

>given a parking car manager, a certain parking-lot with a parked car, and a valid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car success, the ticket should turn to invalid<br>

>given a parking car manager, a certain parking-lot with a parked car, and an invalid ticket relate to it<br>
>when picking up the car from the correct parking-lot<br>
>then picking up car failed<br>

>given an invalid ticket<br>
>when picking up a car<br>
>then picking up failed<br>

>given a car parking manager, a graduate parking attendant, a car to park, two parking-lots with the first one not fully occupied<br>
>when asking the graduate parking attendant to park<br>
>then get a valid ticket from the graduate parking attendant<br>

>given a car parking manager, a graduate parking attendant, no car to park, two parking-lots with the first one not fully occupied<br>d
>when asking the graduate parking attendant to park<br>
>then the graduate parking attendant should refuse to park<br>

>given a car parking manager, a graduate parking attendant, a car to park, two full parking-lots<br>
>when asking the graduate parking attendant to park<br>
>then parking failed<br>

>given a car parking manager, a graduate parking attendant, a car to park, no parking-lot<br>
>when asking the graduate parking attendant to park<br>
>then the graduate parking attendant should refuse to park<br>

>given a car parking manager, a graduate parking attendant, a valid ticket<br>
>when asking the graduate parking attendant to pick up the car<br>
>then the graduate parking attendant should pick up and return the car to the manager<br>

>given a car parking manager, a graduate parking attendant, an invalid ticket<br>
>when asking the graduate parking attendant to pick up the car<br>
>then the graduate parking attendant should fail to pick up a car<br>

>given a car parking manager, a graduate parking attendant, no ticket<br>
>when asking the graduate parking attendant to pick up the car<br>
>then the graduate parking attendant should refuse to pick up<br>

>given a car parking manager, a smart parking attendant, a car to park, two parking-lots with the first one not fully occupied<br>
>when asking the smart parking attendant to park<br>
>then get a valid ticket from the graduate parking attendant<br>

>given a car parking manager, a smart parking attendant, no car to park, two parking-lots with the first one not fully occupied<br>d
>when asking the smart parking attendant to park<br>
>then the smart parking attendant should refuse to park<br>

>given a car parking manager, a smart parking attendant, a car to park, two full parking-lots<br>
>when asking the smart parking attendant to park<br>
>then parking failed<br>

>given a car parking manager, a smart parking attendant, a car to park, no parking-lot<br>
>when asking the smart parking attendant to park<br>
>then the smart parking attendant should refuse to park<br>

>given a car parking manager, a smart parking attendant, a valid ticket<br>
>when asking the smart parking attendant to pick up the car<br>
>then the smart parking attendant should pick up and return the car to the manager<br>

>given a car parking manager, a smart parking attendant, an invalid ticket<br>
>when asking the smart parking attendant to pick up the car<br>
>then the smart parking attendant should fail to pick up a car<br>

>given a car parking manager, a smart parking attendant, no ticket<br>
>when asking the smart parking attendant to pick up the car<br>
>then the smart parking attendant should refuse to pick up<br>

>given a car parking manager, a super parking attendant, a car to park, two parking-lots with the first one not fully occupied<br>
>when asking the super parking attendant to park<br>
>then get a valid ticket from the graduate parking attendant<br>

>given a car parking manager, a super parking attendant, no car to park, two parking-lots with the first one not fully occupied<br>d
>when asking the super parking attendant to park<br>
>then the super parking attendant should refuse to park<br>

>given a car parking manager, a super parking attendant, a car to park, two full parking-lots<br>
>when asking the super parking attendant to park<br>
>then parking failed<br>

>given a car parking manager, a super parking attendant, a car to park, no parking-lot<br>
>when asking the super parking attendant to park<br>
>then the super parking attendant should refuse to park<br>

>given a car parking manager, a super parking attendant, a valid ticket<br>
>when asking the super parking attendant to pick up the car<br>
>then the super parking attendant should pick up and return the car to the manager<br>

>given a car parking manager, a super parking attendant, an invalid ticket<br>
>when asking the super parking attendant to pick up the car<br>
>then the super parking attendant should fail to pick up a car<br>

>given a car parking manager, a super parking attendant, no ticket<br>
>when asking the super parking attendant to pick up the car<br>
>then the super parking attendant should refuse to pick up<br>
