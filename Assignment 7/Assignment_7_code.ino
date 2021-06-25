#include <LiquidCrystal.h>
LiquidCrystal lcd(13,12,5,4,3,2);
void setup() {
  lcd.begin(16, 2);
  lcd.print("Vedant Puranik, PICT");
  delay(1000);
}

void loop() {

  for (int i = 0; i < 21; i++) {
    lcd.scrollDisplayLeft();
    // wait a bit:
    delay(150);
  }


  for (int i = 0; i < 39; i++) {
    lcd.scrollDisplayRight();
    delay(150);
  }


  for (int i= 0; i< 26; i++) {
    lcd.scrollDisplayLeft();
    delay(150);
  }

  delay(2000);

}


 