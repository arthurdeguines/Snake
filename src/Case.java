
public class Case {
	TypeCase type;
	public Case() {
		type = TypeCase.VIDE;
	}
	public Case(TypeCase type) {
		this.type = type;
	}
	public void setCase(TypeCase type) {
		this.type = type;
	}
	public boolean isPlein() {
		if(this.getType() == TypeCase.VIDE) {
			return true;
		}else {
			return false;//
		}
	}
	public TypeCase getType() {
		return this.type;
	}
	public void setType(TypeCase type) {
		this.type = type;
	}


	
}
