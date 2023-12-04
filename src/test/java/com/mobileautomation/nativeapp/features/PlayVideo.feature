@nativeapp
Feature: Play a YouTube video

  As a YouTube app user,
  I want to search for and play videos
  To access the desired content.

  Scenario Outline: Play a video from the search results
    Given the user is using the YouTube application
    When the user performs a search with the keyword "<keyword>"
    And a video is selected from the search results
    Then the selected video is played

    Examples:
      | keyword  |
      | OpenAI   |