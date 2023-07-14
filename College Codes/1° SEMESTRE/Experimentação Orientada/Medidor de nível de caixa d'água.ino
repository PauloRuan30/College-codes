#define CANAL_0 0
#define CANAL_1 1
#define CANAL_2 2
#define CANAL_3 3
#define CANAL_4 4

#define FREQ 5000
#define RESOL 8

#define POTEN 25

#define LED_0 22
#define LED_1_20 21
#define LED_21_40 19
#define LED_41_60 18
#define LED_61_80 5
#define LED_81_100 26

int aux = 0;

void setup() {
  Serial.begin(115200);

  pinMode(POTEN, INPUT);
  pinMode(LED_0, OUTPUT);
  pinMode(LED_1_20, OUTPUT);
  pinMode(LED_21_40, OUTPUT);
  pinMode(LED_41_60, OUTPUT);
  pinMode(LED_61_80, OUTPUT);
  pinMode(LED_81_100, OUTPUT);
  
  ledcSetup(CANAL_0, FREQ, RESOL);
  ledcAttachPin(LED_1_20, CANAL_0);

  ledcSetup(CANAL_1, FREQ, RESOL);
  ledcAttachPin(LED_21_40, CANAL_1);

  ledcSetup(CANAL_2, FREQ, RESOL);
  ledcAttachPin(LED_41_60, CANAL_2);
  
  ledcSetup(CANAL_3, FREQ, RESOL);
  ledcAttachPin(LED_61_80, CANAL_3);
  
  ledcSetup(CANAL_4, FREQ, RESOL);
  ledcAttachPin(LED_81_100, CANAL_4);
}

void loop() {

    //aux = valorPoten;

    int valorPoten = analogRead(POTEN);
    Serial.println(valorPoten);
    
    int potenLed_0 = valorPoten;
    int potenLed_1 = valorPoten;
    int potenLed_2 = valorPoten;
    int potenLed_3 = valorPoten;
    int potenLed_4 = valorPoten;


    if (valorPoten == 0){
      analogWrite(LED_0, HIGH);
    } else {
      analogWrite(LED_0, LOW);
    }

    if (valorPoten > 0 && valorPoten <= 836){
      potenLed_0 = map(potenLed_0, 0, 836, 0, 255);
      ledcWrite(CANAL_0, potenLed_0);
    } 
    
    if (valorPoten > 836 && valorPoten <= 1638) {
      potenLed_1 = map(potenLed_1, 837, 1638, 0, 255);
      ledcWrite(CANAL_1, potenLed_1);
    } 
  
    if (valorPoten > 1638 && valorPoten <= 2457) {
      potenLed_2 = map(potenLed_2, 1639, 2457, 0, 255);
      ledcWrite(CANAL_2, potenLed_2);
    }

    if (valorPoten > 2457 && valorPoten <= 3276) {
      potenLed_3 = map(potenLed_3, 2458, 3276, 0, 255);
      ledcWrite(CANAL_3, potenLed_3);
    }

    if (valorPoten > 3276) {
      potenLed_4 = map(potenLed_4, 3277, 4095, 0, 255);
      ledcWrite(CANAL_4, potenLed_4);
    }  

    /*if (aux < valorPoten){
      if (valorPoten > 0){
        digitalWrite(LED_0, LOW);
      }
      
     if (valorPoten < 819){
      digitalWrite(LED_1_20, LOW);
    } 
    
    if (valorPoten < 1638) {
      digitalWrite(LED_21_40, LOW);
    } 
  
    if (valorPoten < 2457) {
      digitalWrite(LED_41_60, LOW);
    }

    if (valorPoten < 3276) {
      digitalWrite(LED_61_80, LOW);
    }

    if (valorPoten < 4050) {
      digitalWrite(LED_81_100, LOW);
    }  
  }
  delay(100);  */
}