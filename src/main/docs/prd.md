# Parking-lot

##Requirements

###2019/06/15
####Description
With one car and one parking-lot, how do you park your car?
####Tasking
>given "one car, one not full parking-lot"<br>
>when "parking"<br>
>then "parking success and returning a valid ticket related to the car"

>given one car, one full parking-lot<br>
>when "parking"<br>
>then "parking failed"

>given "a parked car, a valid ticket related to the car"<br>
>when "picking up the car"<br>
>then "picking up success and destroying the ticket"

>given "a parked car, a invalid ticket"<br>
>when "picking up the car"<br>
>then "picking up failed"

>given "any parked car, an used ticket"<br>
>when "picking up a car"<br>
>then "picking up failed"

>given "any parked car, no ticket"<br>
>when "picking up a car"<br>
>then "picking up failed"




