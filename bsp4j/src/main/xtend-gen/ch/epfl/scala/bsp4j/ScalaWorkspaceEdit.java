package ch.epfl.scala.bsp4j;

import java.util.List;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SuppressWarnings("all")
public class ScalaWorkspaceEdit {
  private List<ScalaTextEdit> changes;

  public ScalaWorkspaceEdit(final List<ScalaTextEdit> changes) {
    this.changes = changes;
  }

  @Pure
  public List<ScalaTextEdit> getChanges() {
    return this.changes;
  }

  public void setChanges(final List<ScalaTextEdit> changes) {
    this.changes = changes;
  }

  @Override
  @Pure
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("changes", this.changes);
    return b.toString();
  }

  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ScalaWorkspaceEdit other = (ScalaWorkspaceEdit) obj;
    if (this.changes == null) {
      if (other.changes != null)
        return false;
    } else if (!this.changes.equals(other.changes))
      return false;
    return true;
  }

  @Override
  @Pure
  public int hashCode() {
    return 31 * 1 + ((this.changes== null) ? 0 : this.changes.hashCode());
  }
}
