/*     */ package zako.opt.particle;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import java.util.function.IntConsumer;
/*     */ import zako.opt.cpu.CorePin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class Job
/*     */ {
/*     */   final int blocks;
/*     */   final IntConsumer task;
/* 139 */   final AtomicInteger next = new AtomicInteger();
/* 140 */   final AtomicInteger completed = new AtomicInteger();
/* 141 */   final AtomicReference<Throwable> error = new AtomicReference<>();
/*     */   
/*     */   Job(int blocks, IntConsumer task) {
/* 144 */     this.blocks = blocks;
/* 145 */     this.task = task;
/*     */   }
/*     */   
/*     */   void run() {
/* 149 */     CorePin.check();
/*     */     int b;
/* 151 */     while ((b = this.next.getAndIncrement()) < this.blocks) {
/*     */       try {
/* 153 */         if (this.error.get() == null) {
/* 154 */           this.task.accept(b);
/*     */         }
/* 156 */       } catch (Throwable t) {
/* 157 */         this.error.compareAndSet(null, t);
/*     */       } finally {
/* 159 */         this.completed.incrementAndGet();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\particle\Workers$Job.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */