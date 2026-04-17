# Explanation: Single Responsibility Principle

## What was wrong with User_old.java?

When I looked at the User_old class, I saw it had way too much going on inside. It basically did everything: stored user data, validated email, validated password, sent emails, and managed registration. All in one single class.

That's a problem because if I ever needed to change how I validate email, or how I send emails, or the registration process, I had to touch the same class. A class that already had a thousand things. It became fragile and hard to test.

I imagined I just wanted to test if email validation works correctly. I couldn't, because to do that I needed to instantiate the whole class with everything else inside.

## How did I fix it?

I split the responsibilities into 4 classes, each doing one thing:

**User** - Just stores the name, email, and password. Nothing else. Getters and that's it.

**UserValidator** - Only validates. Takes a User and checks if the email and password are valid. If not, throws an exception.

**EmailService** - Just sends emails. If I ever need to change how they're sent, it's here. Nothing else touches it.

**UserRegistrationService** - Coordinates the whole process. It says: "validate the user, send the email, check if they confirmed". It uses the other classes but doesn't have to do the work itself.

## Why is this better?

Now if I want to change the email validation logic, I touch UserValidator. If I want to add more validations, I touch UserValidator. There's nothing in User_old that could break something else unexpectedly.

It's also easier for me to test each part separately. I test UserValidator without having to create the whole registration service. I test if emails are sent correctly without worrying about validation.

And if in the future I need to use validation somewhere else in the program (like in a password change form), I can reuse it without any problems.

## Bottom line

A class should have one responsibility. If User_old changed for any reason, it was broken. Now, each class changes for one specific reason and it's way easier for me to maintain over time.
