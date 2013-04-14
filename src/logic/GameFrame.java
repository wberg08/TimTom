package logic;

import java.util.HashSet;
import java.util.Set;

import logic.entities.ActorEntity;
import logic.entities.BackgroundEntity;
import logic.entities.NonPhysicsActor;
import logic.entities.PhysicsActor;

public class GameFrame {

  private HashSet<BackgroundEntity> bges = new HashSet<BackgroundEntity>();
  private HashSet<PhysicsActor> physicsaes = new HashSet<PhysicsActor>();
  private HashSet<NonPhysicsActor> nonPhysicsaes = new HashSet<NonPhysicsActor>();

  public void step() {
    PhysicsEngine.step(physicsaes, nonPhysicsaes);

    for (BackgroundEntity be : bges)
      be.step(this);
    for (ActorEntity ae : physicsaes)
      ae.step(this);
    for (ActorEntity ae : nonPhysicsaes)
      ae.step(this);
  }

  public void addEntity(BackgroundEntity e) {
    bges.add(e);
  }

  public void addEntity(PhysicsActor e) {
    physicsaes.add(e);
  }

  public void addEntity(NonPhysicsActor e) {
    nonPhysicsaes.add(e);
  }

  public Set<BackgroundEntity> getBackgroundEntities() {
    return bges;
  }

  /*
   * @return A clone of the stored list of ActorEntities.
   */
  public Set<ActorEntity> getActorEntities() {
    @SuppressWarnings("unchecked")
    HashSet<ActorEntity> ret = (HashSet<ActorEntity>) physicsaes.clone();
    ret.addAll(nonPhysicsaes);
    return ret;
  }

}
