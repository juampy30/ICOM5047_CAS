package hkj.sisca.auxiliary;


public class BarrierGateManager {

	public enum BarrierGateActionType {
		OpenBarrierGate, CloseBarrierGate
	}

	//private GpioPinDigitalOutput pin2, pin3;

	public BarrierGateManager() {
		//this.pin2 = Main.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "", PinState.LOW);
		//this.pin3 = Main.gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "", PinState.LOW);
	}

	public boolean performBarrierGateAction(BarrierGateActionType type) {
		switch (type) {
		case OpenBarrierGate:
			System.out.println("Now opening gate...");
			//pin2.high();
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {}
			//pin2.low();
			System.out.println("Gate opened...");
			break;
		case CloseBarrierGate:
			//pin3.high();
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {}
			//pin3.low();
			break;
		}
		return false;
	}

}
