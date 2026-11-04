/*    */ package zako.opt.block;
/*    */ 
/*    */ import lombok.Generated;
/*    */ import net.minecraft.class_2680;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class Key
/*    */ {
/*    */   class_2680 state;
/*    */   int tint;
/*    */   
/*    */   @Generated
/*    */   public Key(class_2680 state, int tint) {
/* 55 */     this.state = state; this.tint = tint; } @Generated
/* 56 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof Key)) return false;  Key other = (Key)o; if (this.tint != other.tint) return false;  Object this$state = this.state, other$state = other.state; return !((this$state == null) ? (other$state != null) : !this$state.equals(other$state)); } @Generated public int hashCode() { int PRIME = 59; result = 1; result = result * 59 + this.tint; Object $state = this.state; return result * 59 + (($state == null) ? 43 : $state.hashCode()); }
/*    */ 
/*    */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\block\MovingBlockCache$Key.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */