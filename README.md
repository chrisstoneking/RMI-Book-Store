# RMI-Book-Store
Project for SST, Seminar 6, CS13
By Christopher Stoneking and Christian Schulz

Goal: Create fictional book store using Java RMI

TODO:
1. Add GUI (Chris)
GUI Details:

- Use CardLayout to display different books -> DONE
- Make use of Add to Cart, should subtract 1 from a number (local var for now, just to test)
- Make use of Show Cart, perhaps popup showing all the books and their quantities?
- Make use of Buy, perhaps as popup showing all the books and the total price?

2. Link to database with JDBC (Christian)
- As of today, the books are just locally created objects
- Database should include: Title, author, price, category, quantity, cover image
- Database extraction should involve: Getting stuff from database, passing it on to where it is needed, i.e. making Book objects


Note: Ideally, the class MainFrame is the only one that the server actually passes on.
MainFrame just calls up the other classes, like Controller, when it needs them.
