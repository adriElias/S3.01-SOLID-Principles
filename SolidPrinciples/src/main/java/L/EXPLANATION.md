# Explanation: Liskov Substitution Principle

## What was wrong with Character.java and Ghost.java?

In the old version, Character has two methods: attack() and takeDamage(). Both Ghost and Warrior extend Character and override both methods.

The problem is Ghost. When you call takeDamage() on a Ghost, it throws an UnsupportedOperationException instead of actually handling damage. That breaks the Liskov Substitution Principle.

If I write code expecting a Character:

```java
public void battle(Character c) {
    c.attack();
    c.takeDamage(10);
}
```

This crashes if you pass a Ghost. The method signature says it accepts any Character, but Ghost doesn't behave like a Character - it breaks the contract by throwing an exception. That violates LSP because Ghost is not truly substitutable for Character.

## How did I fix it?

I separated the concerns:

**Character** - Now it's abstract and only has attack(). No takeDamage() method.

**Damageable** - A new interface that defines takeDamage(int points). This represents anything that can receive damage.

**Ghost** - Extends Character, implements nothing else. It can attack(), it just can't take damage. No exceptions, no broken contracts.

**Warrior** - Extends Character AND implements Damageable. It has both attack() and takeDamage().

## Why is this better?

Now the code is honest. Ghost doesn't pretend to be something it's not. If you need to damage something, you use Damageable. If you just need any character that can attack, you use Character.

When Ghost overrides attack() to attack, it works fine. Ghost is still a valid Character. But Ghost doesn't implement Damageable, so nothing tries to call takeDamage() on it.

Warrior implements both, so it can attack with a sword and also take reduced damage.

## Bottom line

Subclasses must be substitutable for their base class. Ghost couldn't be, so the solution is simple: don't make Ghost pretend to handle something it can't. Use an interface to represent what things can do, and only those things implement it.
