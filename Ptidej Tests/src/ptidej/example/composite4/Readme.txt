A simple architecture that implements a distorted Composite design pattern.
The superclass Paragraph should not know its subclass IndentedParagraph.
The Element class plays the role of Component in the distorted Composite
design pattern, it should not know about the Paragraph class (playing the
role of Leaf).
The Title class plays the role of Leaf in the distorted Composite design
pattern, it should not know about classes Element and Document (playing
the roles of Component and Composite, respectively).