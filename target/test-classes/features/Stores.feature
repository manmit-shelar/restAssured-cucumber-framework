@stores
Feature: Stores API
  
  As a admin user
  I want to access stores information
  So that i can view, add, update, delete store information

  #Scenario 01
  @getAllStores
  Scenario: Multiple records are returned in response of Get Stores API
    Given Request for getting Stores is formed
    When Get call is made for Stores API
    Then Multiple records are received in response of Stores API

  #Scenario 02
  @getSpecificStores
  Scenario: Invalid parameter is passed for Get Specific Store API
    Given Request for getting specific Store is formed with invalid parameter
    When Get call is made for fetching specific Store API
    Then Error response for invalid parameter should be received for Store API

  #Scenario 03
  @getSpecificStores
  Scenario: Non existing store id is passed to Get Specific Store API
    Given Request for getting specific Store is formed with non existing store id
    When Get call is made for fetching specific Store API
    Then Error response for non existing store id should be received for Store API

  #Scenario 04
  @getSpecificStores
  Scenario: Get details of the Specific Store
    Given Request for getting specific Store is formed
    When Get call is made for fetching specific Store API
    Then Details of specific store should be received in response of Get Specific Store API

  #Scenario 05
  @postStore
  Scenario: Mandatory parameters are not passed for Create Store API
    Given Request for Post Store is formed without mandatory parameters
    When Post call is made for Store API
    Then Error response for missing mandatory parameters should be received for Post Store API

  #Scenario 06
  @postStore
  Scenario: New store is created successfully
    Given Request for Post Store is formed
    When Post call is made for Store API
    Then Success response for new store created should be received for Post Store API

  #Scenario 07
  @putStore
  Scenario: Mandatory parameters are not passed for Put Store API
    Given Request for Put Store is formed without mandatory parameters
    When Put call is made for Store API
    Then Error response for missing mandatory parameters should be received for Put Store API

  #Scenario 08
  @putStore
  Scenario: Non existing store id is passed for Put Store API
    Given Request for Put Store is formed with non existing store id
    When Put call is made for Store API
    Then Error response for non existing store id should be received for Put Store API

  #Scenario 09
  @putStore
  Scenario: Existing store is updated successfully
    Given Request for Put Store is formed
    When Put call is made for Store API
    Then Success response for store updated should be received for Put Store API

  #Scenario 10
  @deleteStore
  Scenario: Invalid parameter is passed for Delete Store API
    Given Request for deleting specific Store is formed with invalid parameter
    When Delete call is made for deleting specific Store API
    Then Error response for invalid parameter should be received for Delete Store API

  #Scenario 11
  @deleteStore
  Scenario: Non existing store id is passed to Delete Specific Store API
    Given Request for deleting specific Store is formed with non existing store id
    When Delete call is made for deleting specific Store API
    Then Error response for non existing store id should be received for Delete Store API

  #Scenario 12
  @deleteStore
  Scenario: Delete a specific Store
    Given Request for deleting specific Store is formed
    When Delete call is made for deleting specific Store API
    Then Delete success response should be received in response of Delete Store API
