# Explanation: Interface Segregation Principle

## What was wrong with MachineActions.java?

When I looked at the old MachineActions interface, it had five methods: turnOn(), turnOff(), heat(), cool(), and wash().

That sounds fine until you look at how classes use it. WashingMachine implements MachineActions, but it doesn't use heat() or cool(). It just prints "does not apply". AirConditioner implements MachineActions too, but it doesn't use wash(). It prints "not supported".

This is the Interface Segregation Principle violation. The interface forces classes to depend on methods they don't need. WashingMachine is forced to implement heat() and cool() even though it will never use them. AirConditioner is forced to implement wash() even though it can't do that.

It creates dummy implementations that do nothing useful. And if someone uses these classes, they might accidentally call a method that doesn't work.

## How did I fix it?

I split the big MachineActions interface into smaller, specific ones:

**MachineActions** - Now only has turnOn() and turnOff(). The common actions for all machines.

**Washable** - Only has wash(). Just for things that can wash.

**Heatable** - Only has heat(). Just for things that can heat.

**Coolable** - Only has cool(). Just for things that can cool.

Then I reorganized the classes:

**WashingMachine** - Implements MachineActions (turnOn/turnOff) and Washable (wash). Exactly what it needs.

**AirConditioner** - Implements MachineActions (turnOn/turnOff), Heatable (heat), and Coolable (cool). Exactly what it needs. No wash() anymore.

## Why is this better?

Now each class only implements the methods it actually uses. There are no dummy implementations. If I write code expecting a Washable, I know it has a working wash() method. If I write code expecting a Coolable, I know it has a working cool() method.

The code is also clearer about capabilities. If someone looks at WashingMachine and sees it implements Washable, they immediately know what it can do. If they see AirConditioner implements Coolable and Heatable, they know those features exist.

And if in the future I need only something that can cool but doesn't need to wash, I just look for Coolable. I don't have to wonder if cool() is a real implementation or a dummy one.

## Bottom line

Clients shouldn't be forced to depend on methods they don't use. When an interface gets too big and clients only use parts of it, that's a sign to break it into smaller interfaces. Each interface should represent one capability that classes can choose to implement or not.
