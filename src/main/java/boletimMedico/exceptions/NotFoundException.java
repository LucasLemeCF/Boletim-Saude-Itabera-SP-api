package boletimMedico.exceptions;

public class NotFoundException extends DomainGlobalException{

  public NotFoundException(String errorMessage) {
    super(errorMessage);
  }

}
