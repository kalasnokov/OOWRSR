package AFSFile;

public class Preset {
	public String[] options() {
		String[] s = { "OOWRSR server launch config file", "Debug:true",
				"ICCheck:true" };
		return s;
	}
	public String[] bindings() {
		String[] s = { "OOWRSR key bindings", "Wleft:A", "Wright:D", "Jump:space","Crouch:S","Chat:enter","" };
		return s;
	}
	public String[] creatorUI() {
		String[] s = {"User Input Creator File for the character creator.","Button:0:0:500:100:res/sprites/buttons/exit/:exit:"};
		return s;
	}
}