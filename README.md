The final result of the actual code differs from 
that outlines in the initial 3a design. Most 
significantly, the SudokuGridPanel instead of using an
aggregation of JPanels and JLabels, it manually draws 
rectangles similar to the WordleGridPanel. Two new classes
were added, being the SudokuResponse class to handle storing
numbers and colors for easy interaction between the model and 
view, and the GridMouseAdapter class which handles
mouse input for the SudokuGridPanel class. 