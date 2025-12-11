Feature: SwagLabs shopping
  Login and buy stuff

  Scenario Outline: Login and buy stuff, review the bought stuff and delete it and log out
    Given User is on the home page and goes to log in "<usr>" and "<pwd>"
    When Search for products and adds them to the shopping kart
    Then They can be reviewed in the kart and  logout

    Examples:
      |usr|pwd|
      |standard_user|secret_sauce|

  Scenario Outline: Fail login attempt by using blocked credentials
    Given User is on the home page
    When Attempts to login with blocked credentials "<lckusr>" and "<lckpwd>"
    Then Homepage shows the alert and login fails

    Examples:
      |lckusr|lckpwd|
      |locked_out_user|secret_sauce|
