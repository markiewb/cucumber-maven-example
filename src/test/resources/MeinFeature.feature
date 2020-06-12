Feature: MeinFeature

Scenario: Calc by 3
Given a sequence
  | 100 | 90 | 80 | 70 | 60 |
Given factor = 3
When start the calculation
Then the result must be
  | 300 | 270 | 240 | 210 | 180 |

Scenario: Calc by -3
Given a sequence
  | 100 | 90 | 80 | 70 | 60 |
Given factor = -3
When start the calculation
Then the result must be
  | -300 | -270 | -240 | -210 | -180 |
  
Scenario: Calc by 1
Given a sequence
  | 100 | 90 | 80 | 70 | 60 |
Given factor = 1
When start the calculation
Then the result must be
  | 100 | 90 | 80 | 70 | 60 |

Scenario: Calc by 0 Exception
Given a sequence
  | 100 | 90 | 80 | 70 | 60 |
Given factor = 0
When start the calculation
Then an exception should be thrown
