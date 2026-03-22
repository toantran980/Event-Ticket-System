# Event Ticketing System
### Team Members
- Toan Tran - 881738009
- James Nguyen - 806134391
- Moses Bui - 837586106
- An Nguyen - 885598904

## Video Demo
- Link review video: [Youtube](https://youtu.be/tWyQy8FHPN0?si=vr3pK4SE2JF-wiBD)

## Instructions
- Ensure IntelliJ, Postman, and PostgreSQL (via pgAdmin4) are all configured correctly & running at the same time, as follows:\
• **IntelliJ**: application.properties (use *localhost number*, *password*, and *server.port* specific to your local machine )\
• **PostgreSQL**: create database called "ticket_db" (no quotes)
- In Postman, follow the API requests listed below, in exact order, to demo the full experience.
- For each API Request (*listed below*), the 3rd bullet point contains the JSON body, which you can plug into the "Body" tab on Postman (*see pic below*).
- *Note*: If reading/using the raw README.md file, simply remove any backslashes (\\).
<img width="1096" height="173" alt="image" src="https://github.com/user-attachments/assets/d9a89256-0535-412c-848e-46a15de5f1fa" />

## Below are the API requests needed for Postman testing:

### 1) POST /api/organizers
- Scenario: Live Nation organizer registers
- http://localhost:8080/api/organizers
- {\
  "name": "Live Nation",\
  "email": "contact@livenation.com",\
  "phone": "800-653-8000"\
  }

<img width="710" height="558" alt="image" src="https://github.com/user-attachments/assets/1f9bb218-6728-4118-bbee-880711dc865f"/>\

### 2) POST /api/venues
- Scenario: Staples Center venue registers
- http://localhost:8080/api/venues
- {\
"name": "Staples Center",\
"address": "1111 S Figueroa St",\
"city": "Los Angeles",\
"totalCapacity": 20000\
}

<img width="712" height="566" alt="image" src="https://github.com/user-attachments/assets/d4411f8a-a408-4fc5-992d-95b0b923f58e"/>\

### 3) POST /api/venues
- Scenario: Empire Polo Club venue registers
- http://localhost:8080/api/venues
- {\
"name": "Empire Polo Club",\
"address": "81800 Avenue 51",\
"city": "Indio",\
"totalCapacity": 125000\
}

<img width="712" height="566" alt="image" src="https://github.com/user-attachments/assets/a3b5fe4a-5f26-4824-8112-6167ba5fa61d"/>\


### 4) POST /api/events
- Scenario: Spring Music Fest event registers
- http://localhost:8080/api/events?organizer_id=1&venue_id=1
- {\
"title": "Spring Music Fest",\
"description": "Annual live music festival",\
"eventDate": "2027-03-31T19:30:00",\
"status": "UPCOMING",\
"organizerId": 1,\
"venueId": 1\
}

<img width="714" height="622" alt="image" src="https://github.com/user-attachments/assets/fbed5583-4a08-4227-9e62-a5c458888e62"/>\


### 5) POST /api/events
- Scenario: Coachella event registers
- http://localhost:8080/api/events?organizer_id=1&venue_id=2
- {\
"title": "Coachella",\
"description": "Annual live music festival",\
"eventDate": "2027-04-15T15:00:00",\
"status": "UPCOMING",\
"organizerId": 1,\
"venueId": 2\
}

<img width="713" height="617" alt="image" src="https://github.com/user-attachments/assets/0ee6389e-5e1b-4769-a680-6b5f0fde0fbb"/>\

### 6) GET /api/events
- Scenario: Check all available upcoming events
- http://localhost:8080/api/events
- No body needed.

<img width="712" height="834" alt="image" src="https://github.com/user-attachments/assets/9d4b91f2-bcf5-4d4e-a6a1-af75fce76c00"/>\

### 7) GET event by id
- Scenario: Check Spring Music Fest event
- http://localhost:8080/api/events/1
- No body needed.

<img width="713" height="619" alt="image" src="https://github.com/user-attachments/assets/73fea1a6-2ea8-4cf1-abe2-d5c405206d52"/>

### 8) POST /api/ticket-types/event/1
- Note: Add a VIP ticket tier, for Spring Music Fest
- http://localhost:8080/api/ticket-types/event/1
- {\
"name": "VIP",\
"price": 150.0,\
"quantityAvailable": 50\
}

<img width="712" height="901" alt="image" src="https://github.com/user-attachments/assets/142c1431-fea6-4261-86a3-cc8ed9ffbcd1"/>\

### 9) POST /api/ticket-types/event/1
- Note: Adds a General Admission ticket tier, for Spring Music Fest
- http://localhost:8080/api/ticket-types/event/1
- {\
"name": "General Admission",\
"price": 75.0,\
"quantityAvailable": 100\
}

<img width="712" height="898" alt="image" src="https://github.com/user-attachments/assets/2edb31c0-9572-448d-8864-a228061ba1d5"/>\

### 10) POST /api/attendees
- Scenario: John Wick signs up
- http://localhost:8080/api/attendees
- {\
"name": "John Wick",\
"email": "johnwick@gmail.com"\
}

<img width="713" height="543" alt="image" src="https://github.com/user-attachments/assets/644b7965-14fd-4db4-9195-466d792247d9"/>

### 11) POST /api/bookings
- Scenario: John Wick books a VIP ticket to Spring Music Fest
- http://localhost:8080/api/bookings
- {\
  "attendee": {\
  "attendee_id": 1\
  },\
  "ticketType": {\
  "ticket_type_id": 1\
  }\
  }

<img width="713" height="604" alt="image" src="https://github.com/user-attachments/assets/e788fb87-6ce3-46af-af9e-c7b033f03603"/>

### 12) GET /api/attendees/{1}/bookings
- Scenario: John Wick checks his booking information
- http://localhost:8080/api/attendees/1/bookings
- No body needed.

<img width="710" height="638" alt="image" src="https://github.com/user-attachments/assets/541895ba-1c32-47b1-a8a6-0c7d52cfef91"/>

### 13) GET /api/events/{1}/revenue
- Scenario: Check ticket revenue made, from Spring Music Fest
- http://localhost:8080/api/events/1/revenue
- No body needed.

<img width="713" height="513" alt="image" src="https://github.com/user-attachments/assets/2adb5b6e-f936-4ebd-bda9-f290fdf51ad7"/>

### 14) PUT /api/bookings/1/cancel
- Scenario: John Wick decides to cancel his VIP ticket
- http://localhost:8080/api/bookings/1/cancel
- No body needed.

<img width="713" height="601" alt="image" src="https://github.com/user-attachments/assets/8a75c9d1-2ebc-4e5d-8217-19f3a466e491"/>

### 15) GET /api/attendees/{1}/bookings
- Scenario: John Wick confirms he canceled his VIP ticket booking
- http://localhost:8080/api/attendees/1/bookings
- No body needed.

<img width="712" height="638" alt="image" src="https://github.com/user-attachments/assets/83b9bec0-33d5-45cc-85d1-56baecb43e8a"/>
