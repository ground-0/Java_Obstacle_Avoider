import animation.Scene;
import animation.SceneObject;
import animation.View;
import imt2018027.*;
/*import imt2018010.*;
import imt2018513.*;
import imt2018081.*;
import imt2018517.*;*/
// Driver class to set up and exercise the animation
public class Demo {

	public static void main(String[] args) {
		Scene scene = new DemoScene(); // replace with your implementation

		// populate the scene with objects implemented by the team
		for (int i = 0; i < 6; i++) {
			SceneObject s = new DemoSceneObject(); // replace with your implementation
			s.setPosition(i*50, i * 50); // these will be changed for the demo
			s.setDestPosition(0, 0);
			scene.addObstacle(s); // using appropriate derived classes
		}
/*
		for (int i = 0; i < 2; i++) {
			SceneObject s = new DemoSceneObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}
		for (int i = 2; i < 3; i++) {
			SceneObject s = new imt2018010.DemoSceneObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}

		for (int i = 3; i < 4; i++) {
			SceneObject s = new imt2018081.DemoSceneObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}

		for (int i = 4; i < 5; i++) {
			SceneObject s = new imt2018513.DemoSceneObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}
		for (int i = 5; i < 6; i++) {
			SceneObject s = new imt2018517.DemoSceneObject(); // replace with your implementation
			s.setPosition(i * 50, i * 50);
			scene.addObstacle(s); // using appropriate derived classes
		}*/
		for (int i = 0; i < 6; i++) {
			SceneObject s = new DemoSceneObject(); // replace with your implementation
			s.setPosition(500 - i * 50, 300 + i * 50); // these will be changed for the demo
			s.setDestPosition(0, 0);
			scene.addActor(s); // using appropriate derived classes
		}

		//View view = new DemoTextView();
		View view = new DemoSwingView();

		scene.setView(view);

		view.init();

	}

}
