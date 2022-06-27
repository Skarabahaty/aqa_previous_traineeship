package models.delete_post;

public class DeletePostInitialResponse {

    private final int response;

    public DeletePostInitialResponse(int response) {
        this.response = response;
    }

    public boolean ifDeletionSuccessful() {
        return response == 1;
    }
}
