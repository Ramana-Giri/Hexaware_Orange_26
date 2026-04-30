Feature: Verify Login module
 
  Scenario: Execute valid username and password testcase
    Given enter the url
    When enter valid username "Admin"  and password "admin123"
    Then click on Login button