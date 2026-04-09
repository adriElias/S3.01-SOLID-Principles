# 🧬 L - Liskov Substitution Principle (LSP)

## 🧠 What is it?

The **Liskov Substitution Principle (LSP)** states that:

> **Subclasses must be able to replace their superclasses without altering the program's behavior.**

This means that any subclass should **behave like its parent class**. If a subclass **breaks contracts or behaviors** defined in the base class, it violates this principle.

In other words, if a program is designed to work with an object of a base class, it should work correctly if that object is replaced with any object of a derived class.

## 🚨 Why is it important?

When we use **inheritance**, we expect a subclass to **extend the behavior of the superclass, not break it**. If LSP is not respected, we can have hard-to-detect errors and systems that are difficult to maintain or extend.

### 👩‍🏫 **Example:**

Suppose you are designing an application that works with various **cryptocurrencies** and offers an **API** like this:

```java
public class Wallet {
    private String cryptoName;
    private String cancellationCode;

    public Wallet(String cryptoName, String cancellationCode) {
        this.cryptoName = cryptoName;
        this.cancellationCode = cancellationCode;
    }

    public void sendMoney(String destination, double amount) {
        System.out.println("Sending money via " + cryptoName + " blockchain");
    }

    public void cancelTransaction(String transactionId) {
        if (AuthorizationCancel.cancel(cancellationCode, transactionId))
            System.out.println("Transaction " + transactionId + " cancelled with code " + cancellationCode);
        else throw new TransactionCancelException("Could not cancel transaction");
    }
}
```
**Your API works with:**

- `Tezos`
- `Ethereum`
- `Monero`

```java
public class TezosWallet extends Wallet {
    public TezosWallet() {
        super("Tezos", "TEZ_0974_BLCH");
    }
}

public class EthereumWallet extends Wallet {
    public EthereumWallet() {
        super("Ethereum", "ETH_7637_BLCH");
    }
}

public class MoneroWallet extends Wallet {
    public MoneroWallet() {
        super("Monero", null); // Monero does not allow transaction cancellation
    }
}
```
🔴 Problem: `MoneroWallet` inherits from `Wallet`, but when someone calls `cancelTransaction()` on a `MoneroWallet` instance, the **program will always crash** because Monero doesn't allow cancellations.

⚠️ Therefore, you cannot use `MoneroWallet` in contexts that expect `Wallet.cancelTransaction()` to work correctly → this **breaks LSP**.

✅ Solution: Apply LSP with a **clear hierarchy**:
> The key is to **separate responsibilities**: not all cryptocurrencies allow cancellations, so not all wallets should have this method.

- **1️⃣ Create an interface for cancellation:**

```java
public interface CancellationStrategy {
    void cancel(String id);
}
```

- **2️⃣ Implement the actual strategy and null strategy:**

```java
public class AllowedCancellationStrategy implements CancellationStrategy {
    private String cancellationCode;

    public AllowedCancellationStrategy(String cancellationCode) {
        this.cancellationCode = cancellationCode;
    }

    @Override
    public void cancel(String id) {
        System.out.println("Transaction " + id + " cancelled with code " + cancellationCode);
    }
}

public class NotAllowedCancellationStrategy implements CancellationStrategy {
    @Override
    public void cancel(String id) {
        // Does nothing, simply ignores the cancellation
        System.out.println("Cancellation not supported for this wallet, ignoring transaction: " + id);
    }
}

```
- **3️⃣ Modify the Wallet class to use the cancellation strategy:**

```java
public class Wallet {
    private String cryptoName;
    private CancellationStrategy cancellationStrategy;

    public Wallet(String cryptoName, CancellationStrategy cancellationStrategy) {
        this.cryptoName = cryptoName;
        this.cancellationStrategy = cancellationStrategy;
    }

    public void sendMoney(String destination, double amount) {
        System.out.println("Sending money via " + cryptoName + " blockchain");
    }

    public void cancelTransaction(String id) {
        cancellationStrategy.cancel(id);
    }
}
```

- **4️⃣ Create wallets with the appropriate strategy:**

```java
public class TezosWallet extends Wallet {
    public TezosWallet() {
        super("Tezos", new AllowedCancellationStrategy("TEZ_0974_BLCH"));
    }
}

public class EthereumWallet extends Wallet {
    public EthereumWallet() {
        super("Ethereum", new AllowedCancellationStrategy("ETH_7637_BLCH"));
    }
}

public class MoneroWallet extends Wallet {
    public MoneroWallet() {
        super("Monero", new NotAllowedCancellationStrategy());
    }
}
```

---

## 🎯 Exercise Objective

You will find a Java class that **incorrectly uses inheritance** and, as a result, **violates the Liskov Substitution Principle**.

🔧 Your challenge is:

1. Identify the hierarchy that **breaks expected behavior**.
2. Refactor the code to ensure that **subclasses are substitutable without breaking** the logic.
3. Apply **abstractions and polymorphism** to make the code more flexible and robust.

---

## 📌 Tips for applying LSP

✅ **Ensure that all subclasses comply with the superclass contract.**

✅ **Don't use inheritance just to reuse code.**

✅ **Consider Composition* over Inheritance when there is no clear type relationship.**

`*` **"Composition"** is an **OOP** concept that means building a class using other objects (from other classes) as internal parts, instead of creating an inheritance hierarchy (subclasses).

---


## 💬 Reflection

When you correctly apply **the Liskov Substitution Principle**:
- You avoid unexpected behavior during execution.
- Your code is more predictable, safe, and reusable.
- You can use polymorphism without surprises.

🔁 **Inheriting** behavior means **respecting it**, **not breaking it**.

---

🚀 Let's go! Review the code, identify the error, and refactor the hierarchy to comply with **LSP**.

❓ **Can a subclass be used in place of its superclass without issues?**

---

## ✅ Solution - Folder Structure

Here's the recommended folder structure after applying the LSP principle:

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
│   │       │   ├── README.md                 # This file with documentation
│   │       │   ├── _new/                     # Refactored solution
│   │       │   │   ├── Character.java        # Abstract base class for all characters
│   │       │   │   ├── Damageable.java       # Interface: defines damage behavior
│   │       │   │   ├── Ghost.java            # Implementation: Ghost character
│   │       │   │   ├── Warrior.java          # Implementation: Warrior character (implements Damageable)
│   │       │   │   └── Main.java             # Main class to test the solution
│   │       │   │
│   │       │   └── _old/                     # Original violation of LSP (for reference)
│   │       │       └── Character.java        # Example breaking LSP
│   │       │
│   │       ├── O/                            # Open/Closed Principle examples
│   │       │   ├── README.md
│   │       │   ├── _new/                     # Refactored solution
│   │       │   └── _old/                     # Original violation
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
│           │   └── CharacterTest.java        # Unit tests for characters
│           │
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
| **Character** | Abstract base class defining the contract for all characters |
| **Damageable** | Interface defining the contract for entities that can take damage |
| **Ghost** | Concrete implementation of Character (does not implement Damageable) |
| **Warrior** | Concrete implementation of Character that implements Damageable |
| **Main** | Entry point and usage demonstration |

### Key Benefits

✅ **Character** - Defines the common behavior for all characters
✅ **Ghost** - Can be used as Character without issues
✅ **Warrior** - Can be used as Character and implements optional Damageable interface
✅ **Substitutable** - Ghost and Warrior can be used interchangeably where Character is expected
✅ **No breaking behavior** - All characters can attack without breaking contracts

Each class follows **LSP**: subclasses can be substituted for their parent class without breaking behavior ✨


