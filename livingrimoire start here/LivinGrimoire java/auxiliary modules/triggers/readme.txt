trigger classes :
these are custom boolean triggers.
they all must extend the TrGEV3 class so if needed you can combine 
multiple triggers using an
EV3DaisyChain object.
the trigger classes have a naming convention : their name should start with 
Trg.
there are 4 main types of trg objects. based on the DiParrot skill, these are 
the triggers responsible
to make a waifubot feel alive to the user :
event(time triggered) :
for example :
at 5 o'clock
reply :
triggered by certain input
stand by :
accumulated input triggered
for example :
trigger after x minutes of no input
or trigger after being cussed at for y times
or after getting patted so and so times by the user
togglers :
these are basically like mood swings
the gates logic varies and will react(return false or true) differently based
on prev input
friend :
these can be considered a toggler triggers
it checks if a friend is present. and so the behavior or actions can very
to that context, for example, be more talkative