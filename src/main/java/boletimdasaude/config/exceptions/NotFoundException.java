package boletimdasaude.config.exceptions;

public class NotFoundException extends DomainGlobalException{

  public NotFoundException(String errorMessage) {
    super(errorMessage);
  }

}
