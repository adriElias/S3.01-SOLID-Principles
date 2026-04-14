# Explanation: Open/Closed Principle

## What was wrong with InstrumentPlayer.java?

When I looked at the InstrumentPlayer class, I saw it was full of if-else statements. One for guitar, one for drums, one for piano. Every time someone wanted to play an instrument, the code checked what it was and then did something different.

That's a problem because if I ever needed to add a new instrument, I had to go back and modify the InstrumentPlayer class. Add another if-else block, compile again, test the whole thing. The class was closed for modification, but it should have been open for extension.

Imagine I want to add a violin next week. I have to change the existing code, which means I could accidentally break something that was already working. And if someone else is using this class in their code, my changes might break their stuff too.

## How did I fix it?

I created an interface called Instrument that says: "anything that plays an instrument must have a play() method."

Then I created separate classes for each instrument:

**Instrument** - An interface that defines a simple contract: play a sound.

**Guitar** - Implements Instrument and plays the guitar sound. That's it, nothing else.

**Drums** - Implements Instrument and plays the drum sound.

**Piano** - Implements Instrument and plays the piano sound.

**InstrumentPlayer** - Now it just receives an Instrument and calls its play() method. No if-else, no checking what instrument it is. It doesn't need to.

## Why is this better?

Now if I want to add a violin, I just create a new Violin class that implements Instrument. The InstrumentPlayer class doesn't change at all. I don't modify existing code, I just extend it with new classes.

This means I can't accidentally break anything. The InstrumentPlayer is now closed for modification but open for extension. I can add violins, flutes, saxophones, whatever, without touching the original code.

It's also cleaner. Each instrument knows how to play itself. The InstrumentPlayer doesn't need to know the details of how each instrument works.

## Bottom line

Classes should be open for extension but closed for modification. If I had to edit InstrumentPlayer every time someone added a new instrument, the code would be fragile and brittle. Now I can extend functionality without changing anything that's already there.
