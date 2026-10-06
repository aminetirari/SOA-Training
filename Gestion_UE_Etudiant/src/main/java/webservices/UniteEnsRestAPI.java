package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnsRestAPI {
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();
    //getListUEs
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListUe(){
        return Response.status(200).entity(helper.getListeUE()).build();
    }
    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addUniteEnseignement(UniteEnseignement ue){
        if (helper.addUniteEnseignement(ue)){
            return Response.status(201).entity("Succes").build();
        }
        else {
            return Response.status(400).entity("Erreur").build();
        }
    }
    @Path("/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEByCode(@PathParam("code") int code){
        return Response.status(200).entity(helper.getUEByCode(code)).build();
    }

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUEBySemestre(@QueryParam("semestre") int semestre){
        return Response.status(200).entity(helper.getUEBySemestre(semestre)).build();
    }

    @Path("/{code}")
    @DELETE
    public Response deleteUEByCode(@PathParam("code") int code){
        if (helper.deleteUniteEnseignement(code)){
            return Response.status(200).entity("deleted successfully").build();
        } else {
            return Response.status(404).entity("delete failed").build();
        }
    }


    @Path("/{code}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUE(@PathParam("code") int code, UniteEnseignement ue){
        if (helper.updateUniteEnseignement(code, ue)){
            return Response.status(200)
                    .entity("updated successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("fail update")
                    .build();
        }
    }


}