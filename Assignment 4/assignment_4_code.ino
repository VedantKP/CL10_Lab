// C++ code
//

int sensorValue = 0;

void setup()
{
  pinMode(A5, INPUT);
  pinMode(8, OUTPUT);
}

void loop()
{
    sensorValue = analogRead(A5);
    Serial.println(sensorValue);
    if(sensorValue < 400)
    {
      digitalWrite(8,HIGH);
    }
    else
    {
      digitalWrite(8,LOW);
    }
  delay(200);
}