Feature: Wordcounter

Scenario: Access Word counter and count a paragraph
  Given the user access the webpage wordcounter
  When the user sends a paragraph of "25" words
  Then the user will verify the amount of words and characters on the text sent

Scenario: The user checks the top 3 words on a paragraph
  Given the user access the webpage wordcounter
  When the user sends a paragraph of "25" words
  Then the user verifies the top "3" words on the paragraph and the number of appearances

