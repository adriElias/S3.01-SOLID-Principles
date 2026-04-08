# 🚪 O - Open/Closed Principle (OCP)

## 🧠 What is it?

The **Open/Closed Principle** states that:

> **Code should be open for extension, but closed for modification**

This means we should be able to **add new functionalities** to our system **without modifying existing code**.


### 👩‍🏫 **Example:**

Suppose you have a `DiscountCalculator` class with this method:

```java
public double calculateDiscount(Product product) {
    if (product.getType().equals("Christmas")) {
        return product.getPrice() * 0.2;
    } else if (product.getType().equals("BlackFriday")) {
        return product.getPrice() * 0.3;
    }
    return 0;
}
```
🔴 Problem: Every time you want to add a new discount type, **you have to modify this class**.
⚠️ This **violates the OCP**, because the code is not closed to modification.


✅ The solution is to use **polymorphism** or **design patterns** (such as **Strategy** or **Factory**), which allow adding new behaviors without touching existing code:

- **1️⃣ Define a common interface:**

```java
public interface DiscountStrategy {
    double applyDiscount(Product product);
}
```
- **2️⃣ Implement concrete strategies:**

```java
public class ChristmasDiscount implements DiscountStrategy {
    public double applyDiscount(Product product) {
        return product.getPrice() * 0.2;
    }
}

public class BlackFridayDiscount implements DiscountStrategy {
    public double applyDiscount(Product product) {
        return product.getPrice() * 0.3;
    }
}

public class AnniversaryDiscount implements DiscountStrategy {
    public double applyDiscount(Product product) {
        return product.getPrice() * 0.1;
    }
}

```
- **3️⃣ Flexible DiscountCalculator open for extension:**

```java

public class DiscountCalculator {

    public double calculateDiscount(Product product, DiscountStrategy strategy) {
        return strategy.applyDiscount(product);
    }
}
````
- **4️⃣ Usage example:**

```java
public class Main {
    public static void main(String[] args) {
        Product product = new Product("Laptop", 1000);

        DiscountCalculator calculator = new DiscountCalculator();
 
        double discount = calculator.calculateDiscount(product, new ChristmasDiscount());
        System.out.println("Discount applied: " + discount);
    }
}
```
---

## 🎯 Exercise Objective

In the attached Java file you will find a class that **does not respect the OCP principle**: it needs to be modified every time there is a change or extension of functionality.

🔧 Your challenge is:

1. Identify which part of the code is **too exposed to modifications**.
2. Refactor it to be **easily extensible** without altering existing behavior.
3. Apply **abstractions and polymorphism** to make the code more flexible and robust.

---

## 📌 Tips for applying OCP

✅ **Avoid conditional statements (if/else, switch)** to decide behaviors that may vary over time.

✅ **Define interfaces or abstract classes** that allow adding new functionalities without touching existing code.

✅ **Use patterns like Strategy, Factory, or Chain of Responsibility** depending on the context.

---


## 💬 Reflection

When a system is well designed according to **OCP**:
- You can add **new functionalities** easily.
- Your code is **more stable** and less vulnerable to regressions*.
- You improve **reusability** and **maintainability**.

🔁 **Extensible**, yet **safe**. This is the power of OCP.

`*` **Regression** means that functionality that **previously worked correctly now stops working** after making changes to the code.

---

🚀 Let's go! Review the code, identify how it can be improved, and apply the OCP principle to make it more modular and future-proof.

❓ **Can you add a new instrument without changing the code?**
---

## ✅ Solution - Folder Structure

Here's the recommended folder structure after applying the OCP principle:

```
SolidPrinciples/
├── pom.xml                                    # Maven configuration file
├── README.md                                  # Project documentation
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── D/                            # Dependency Inversion Principle examples
│   │       ├── I/                            # Interface Segregation Principle examples
│   │       ├── L/                            # Liskov Substitution Principle examples
│   │       ├── O/                            # Open/Closed Principle examples
│   │       │   ├── README.md                 # This file with documentation
│   │       │   ├── _new/                     # Refactored solution
│   │       │   │   ├── Instrument.java       # Abstract class: base for all instruments
│   │       │   │   ├── Drums.java            # Implementation: Drums instrument
│   │       │   │   ├── Guitar.java           # Implementation: Guitar instrument
│   │       │   │   ├── Piano.java            # Implementation: Piano instrument
│   │       │   │   ├── InstrumentPlayer.java # Orchestrates instrument playing (open for extension)
│   │       │   │   └── Main.java             # Main class to test the solution
│   │       │   │
│   │       │   └── _old/                     # Original violation of OCP (for reference)
│   │       │       └── InstrumentPlayer.java # Example with if/else conditions
│   │       │
│   │       └── S/                            # Single Responsibility Principle examples
│   │           ├── README.md
│   │           ├── _new/                     # Refactored solution
│   │           └── _old/                     # Original violation
│   │
│   └── test/
│       └── java/
│           ├── D/                            # Tests for Dependency Inversion
│           ├── I/                            # Tests for Interface Segregation
│           ├── L/                            # Tests for Liskov Substitution
│           ├── O/                            # Tests for Open/Closed
│           │   └── InstrumentPlayerTest.java # Unit tests for instruments
│           │
│           └── S/                            # Tests for Single Responsibility
│               └── UserTest.java             # Unit tests for User and related classes
│
└── target/                                    # Maven build output (generated)
    ├── classes/                              # Compiled main classes
    └── test-classes/                         # Compiled test classes
```

### Description of each class

| Class | Responsibility |
| --- | --- |
| **Instrument** | Abstract base class defining the contract for all instruments |
| **Drums** | Implementation of Drums instrument |
| **Guitar** | Implementation of Guitar instrument |
| **Piano** | Implementation of Piano instrument |
| **InstrumentPlayer** | Orchestrates instrument playing without knowing specific types |
| **Main** | Entry point and usage demonstration |

### Key Benefits

✅ **Instrument** - Defines the common interface for all instruments
✅ **Drums, Guitar, Piano** - Each implements the instrument interface differently
✅ **InstrumentPlayer** - Works with any instrument without modification
✅ **Extensible** - Add new instruments (Violin, Flute, etc.) without changing InstrumentPlayer

Each class follows **OCP**: you can extend with new instruments without modifying existing code ✨
