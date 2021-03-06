package org.danny.messenger.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.danny.messenger.model.Comment;
import org.danny.messenger.service.CommentService;

@Path("/") // optional for Sub Resources
public class CommentResource {

    private CommentService commentService = new CommentService();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentService.getAllComments(messageId);
    }

    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long commentId) {
        return commentService.getComment(messageId, commentId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId, Comment comment) {
        return commentService.addComment(messageId, comment);
    }

    @PUT
    public Comment updateComment(@PathParam("messageId") long messageId, Comment comment) {
        return commentService.updateComment(messageId, comment);
    }

    @DELETE
    public void deleteComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long commentId) {
        commentService.removeComment(messageId, commentId);
    }

}
