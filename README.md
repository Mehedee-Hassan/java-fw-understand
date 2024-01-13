## Design pattern

### factory design pattern
```
+-------------------------------------+
|            Toy (Interface)          |
+-------------------------------------+
      ^                   ^
      |                   |
      |                   |
+------------+     +------------+
|  CarToy    |     |  DollToy   |
+------------+     +------------+
(Implements)       (Implements)

+-------------------------------------+
|         ToyFactory (Class)          |
| + createToy(type: String): Toy      |
+-------------------------------------+
          ^
          |
          |
+--------------------------+
|  RealToyFactory (Class)  |
| + createToy(...)         |
+--------------------------+
(Extends or Implements)

```

### How jpa uses this:
```
[Application Layer]
    | (uses)
[UserRepository Interface] ---(extends)---> [JpaRepository]
    |
    |---(detected by)
[Spring Data Mechanism]
    |
    |---(creates)
[Proxy Implementation of UserRepository]
    |
    |---(invokes)
[Database Operations]

```

- ```
  How jpa handles findId(id=1) , compared to aboce toyfactory example
  How select * from table where id = 1 created at run time
  ```
```
[Application] calls
   |
[UserRepository.findById(1)] in
   |
[Spring Data JPA Proxy]
   | interprets and translates
   v
[SQL Query: "SELECT * FROM user_table WHERE id = 1"] executed on
   |
[Database]
   |
   v
[Result Returned]

```

## proxy interface
