package org.firstinspires.ftc.teamcode.Util;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;

import java.lang.reflect.Array;

public class PedroController implements Action{
    PathChain pathChain = null;
    Path path = null;
    Follower follower;
    public PedroController(Follower follower, PathChain pathChain){
        this.follower = follower;
        this.pathChain = pathChain;
    }
    public PedroController(Follower follower, Path path){
        this.follower = follower;
        this.path = path;
    }
    public PedroController(Follower follower){
        this.follower = follower;
    }
    public void update() {
        if(path != null){follower.followPath(path); } else { follower.followPath(pathChain); }
        follower.update();
    }
    public boolean isFinished() {
        return follower.atParametricEnd();
    }
    public PedroController followPath(PathChain pathChain){
        this.pathChain = pathChain;
        path = null;
        return this;
    }
    public PedroController followPath(Path path){
        this.path = path;
        pathChain = null;
        return this;
    }
}
