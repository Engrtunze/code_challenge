# Transaction Service 💰
The Transaction Service is a component that manages transactions and provides statistics based on the transactions within the last 30 seconds

## Implementation Overview 👨‍💻
The `TransactionServiceImpl` class is an implementation of the TransactionService interface. It provides the following functionalities:

- Add a transaction with a timestamp and amount
- Get statistics of transactions within the last 30 seconds
- Delete all transactions
- Get all transactions

The key components of the implementation are as follows:

- `transactions`: A `Deque` data structure to store the transactions in chronological order i.e:maintaining the order of insertion, allowing you to add or remove elements from both ends of the queue. 
```
Benefit of using Deque are
- It preserves the order of transactions based on their timestamps.
- It allows us to efficiently add new transactions to the end and remove transactions from the beginning or end.
- It provides constant time (O(1)) complexity for adding or removing elements from either end.
```
- `sum`, `max`, `min`: `AtomicReference` variables to hold the sum, maximum, and minimum values of transaction amounts.
- `count`: An AtomicLong variable to keep track of the number of transactions.
- `lock`: An Object used for synchronization to ensure thread-safety.

## Dependencies 🧪
The project relies on the following dependencies:

Spring Boot Starter Web: Provides the necessary components and features for building RESTful APIs in a Spring Boot application.
Lombok: Used for reducing boilerplate code and improving code readability.

## Adding a Transaction
The `addTransaction` method takes a `TransactionRequest` object as input and adds the transaction to the transactions queue. It performs the following validations:

- Checks if the transaction timestamp is in the future.
- Checks if the transaction is older than 30 seconds.
If the validations pass, the transaction is added to the queue, and the sum, max, min, and count values are updated accordingly.

## Getting Transaction Statistics
The `getStatistics` method returns the statistics of transactions within the last 30 seconds. It checks if the `transactions` queue is empty or if the first transaction in the queue is older than 30 seconds. If either condition is true, a `NotFoundException` is thrown. Otherwise, the sum, max, min, average, and count values are calculated and returned as a `TransactionStatistics` object.

## Deleting Transactions
The `deleteTransactions` method clears the `transactions` queue and resets the sum, max, min, and count values to their initial states.

## Getting All Transactions
The `getAllTransaction` method returns a list of all transactions in the `transactions` queue. If the queue is empty, a `NotFoundException` is thrown.

## Usage
To run the project:

- Make sure you have Java and Maven installed.
- Clone the project from the repository.
- Navigate to the project directory.
- Run mvn spring-boot:run to start the application.
- Access the API endpoints using a REST client (e.g., cURL, Postman).
- Access the API documentation on swagger using  http://localhost:8080/swagger-ui/index.html#

## Error Handling 🐞
The `TransactionServiceImpl` class handles various exceptions to ensure data integrity and proper functioning:

- `DateTimeParseException`: Thrown when the timestamp provided is invalid or not in the correct format.
- `ThirtySecondAgoException`: Thrown when the transaction time is older than 30 seconds.
- `NotFoundException`: Thrown when no transactions are found within the last 30 seconds or when trying to retrieve all transactions but none are available.

## Conclusion
This project demonstrates the implementation of a Transaction Statistics RESTful API in Java. By following the specified requirements and design considerations, the API provides efficient and thread-safe calculation of real-time statistics for transactions within the last 30 seconds. The use of the `Deque` data structure in this implementation helps us manage the transactions effectively and maintain the desired order and efficiency for adding and removing transactions which provides constant time (O(1)) complexity for adding or removing elements from either end. , and the project's structure enables successful Maven builds and tests.

# Algorithm Solution
This class provides solutions to various algorithmic problems. It includes two main algorithms: sumTwoNumbers and numberRange.

## Implementation Overview
`sumTwoNumbers`
The sumTwoNumbers method determines if there are any two numbers in the given array that sum up to a target value. It iterates over the array and uses a set data structure to keep track of the complement of each number. If the complement exists in the set, it means a pair of numbers that sum up to the target value has been found. This method provides a time complexity of O(n), where n is the size of the input array.

`numberRange`
The numberRange method finds the low and high indices of a target value in a sorted array. It uses a binary search algorithm to efficiently locate the target value. If the target value is found, it expands the range to include all occurrences of the target value by checking the neighboring elements. This method provides a time complexity of O(log n), where n is the size of the input array.

## Usage
To use test algorithms in this project:
navigate to the test directory `src/test/java/com/seerbit_assesement/code_challenge/algorithmSolutions/AlgorithmSolutionsTest.java` to run a test.


# Additional Note 😥
_Please with a sincere apology 😥 i wasn't able to complete the last algorithm task mentioned in the assessment because it might take more time to figure out due to the limited time provided to complete all tasks. I apologize for any inconvenience caused by not providing a solution for the third task. I assure you that i am committed to delivering high-quality solutions within a reasonable time frame._
