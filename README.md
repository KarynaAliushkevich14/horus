# Zadanie:
Na podstawie istniejącego kodu napisać implementację metod findBlockByColor(), findBlocksByMaterial(), count()

# Rozwiązanie:
W istniejącym programie możemy zauważyć, że interface CompositeBlock rozszerza interface Block, natomiast metoda List<Block> getBlocks() znajduje się w interfejsie CompositeBlock.
Oznacza to, że zmienna List<Block> blocks w klasie Wall może przechowywać nie tylko zwykłe bloki, ale także bloki kompozytowe składające się z innych bloków.
Wszystkie trzy metody sprawdzają typ bloku i w zależności od tego rozwiązują problem.


# Task:
Based on the existing code, write an implementation of the method findBlockByColor(), findBlocksByMaterial(), count()

# Solution:
In the existing program, we can notice that interface CompositeBlock extends interface Block, while the List<Block> getBlocks() method is located in the CompositeBlock interface.
This means that the List<Block> blocks variable in the Wall class can store not only ordinary blocks, but also composite blocks consisting of other blocks.
All three methods check the block type and solve the problem based on this.

