/*     */ package zako.opt.particle;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import java.util.function.IntConsumer;
/*     */ import zako.opt.cpu.CorePin;
/*     */ 
/*     */ public final class Workers {
/*     */   @Generated
/*     */   private Workers() {
/*  12 */     throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
/*  13 */   } static final int COUNT = Math.max(1, Math.min(8, Runtime.getRuntime().availableProcessors() - 2));
/*  14 */   private static final AtomicInteger THREAD_ID = new AtomicInteger(); private static final ExecutorService POOL; static {
/*  15 */     POOL = Executors.newFixedThreadPool(COUNT, r -> {
/*     */           Thread t = new Thread(r, "zakoopt-worker-" + THREAD_ID.incrementAndGet());
/*     */           t.setDaemon(true);
/*     */           return t;
/*     */         });
/*     */   }
/*     */   
/*     */   public static Throwable run(int blocks, IntConsumer task) {
/*  23 */     Job job = new Job(blocks, task);
/*  24 */     for (int h = Math.min(COUNT, blocks - 1); h > 0; h--) {
/*  25 */       Objects.requireNonNull(job); POOL.execute(job::run);
/*     */     } 
/*     */     
/*  28 */     job.run();
/*  29 */     while (job.completed.get() < blocks) {
/*  30 */       Thread.onSpinWait();
/*     */     }
/*  32 */     return job.error.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public static Stream stream(IntConsumer task) {
/*  37 */     Stream stream = new Stream(task);
/*  38 */     stream.start();
/*  39 */     return stream;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Stream idleStream(IntConsumer task) {
/*  44 */     return new Stream(task);
/*     */   }
/*     */   
/*     */   public static final class Stream {
/*     */     final IntConsumer task;
/*  49 */     final AtomicInteger next = new AtomicInteger();
/*  50 */     final AtomicInteger completed = new AtomicInteger();
/*  51 */     final AtomicReference<Throwable> error = new AtomicReference<>();
/*     */     volatile int ready;
/*  53 */     volatile int total = -1;
/*     */     private boolean started;
/*     */     
/*     */     Stream(IntConsumer task) {
/*  57 */       this.task = task;
/*     */     }
/*     */ 
/*     */     
/*     */     public void start() {
/*  62 */       if (this.started) {
/*     */         return;
/*     */       }
/*  65 */       this.started = true;
/*  66 */       for (int h = Workers.COUNT; h > 0; h--) {
/*  67 */         Workers.POOL.execute(this::work);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void publish(int count) {
/*  73 */       this.ready = count;
/*     */     }
/*     */ 
/*     */     
/*     */     public Throwable finish(int count) {
/*  78 */       this.ready = count;
/*  79 */       this.total = count;
/*  80 */       work();
/*  81 */       while (this.completed.get() < count) {
/*  82 */         Thread.onSpinWait();
/*     */       }
/*  84 */       return this.error.get();
/*     */     }
/*     */ 
/*     */     
/*     */     public Throwable await(int count) {
/*  89 */       this.ready = Math.max(this.ready, count);
/*  90 */       while (this.completed.get() < count) {
/*  91 */         int n = this.next.get();
/*  92 */         if (n < count && this.next.compareAndSet(n, n + 1)) {
/*     */           try {
/*  94 */             if (this.error.get() == null) {
/*  95 */               this.task.accept(n);
/*     */             }
/*  97 */           } catch (Throwable t) {
/*  98 */             this.error.compareAndSet(null, t);
/*     */           } finally {
/* 100 */             this.completed.incrementAndGet();
/*     */           }  continue;
/*     */         } 
/* 103 */         Thread.onSpinWait();
/*     */       } 
/*     */       
/* 106 */       return this.error.get();
/*     */     }
/*     */     
/*     */     void work() {
/* 110 */       CorePin.check();
/*     */       while (true) {
/* 112 */         int n = this.next.get();
/* 113 */         if (n < this.ready) {
/* 114 */           if (this.next.compareAndSet(n, n + 1)) {
/*     */             try {
/* 116 */               if (this.error.get() == null) {
/* 117 */                 this.task.accept(n);
/*     */               }
/* 119 */             } catch (Throwable throwable) {
/* 120 */               this.error.compareAndSet(null, throwable);
/*     */             } finally {
/* 122 */               this.completed.incrementAndGet();
/*     */             } 
/*     */           }
/*     */           continue;
/*     */         } 
/* 127 */         int t = this.total;
/* 128 */         if (t >= 0 && n >= t) {
/*     */           return;
/*     */         }
/* 131 */         Thread.onSpinWait();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Job {
/*     */     final int blocks;
/*     */     final IntConsumer task;
/* 139 */     final AtomicInteger next = new AtomicInteger();
/* 140 */     final AtomicInteger completed = new AtomicInteger();
/* 141 */     final AtomicReference<Throwable> error = new AtomicReference<>();
/*     */     
/*     */     Job(int blocks, IntConsumer task) {
/* 144 */       this.blocks = blocks;
/* 145 */       this.task = task;
/*     */     }
/*     */     
/*     */     void run() {
/* 149 */       CorePin.check();
/*     */       int b;
/* 151 */       while ((b = this.next.getAndIncrement()) < this.blocks) {
/*     */         try {
/* 153 */           if (this.error.get() == null) {
/* 154 */             this.task.accept(b);
/*     */           }
/* 156 */         } catch (Throwable t) {
/* 157 */           this.error.compareAndSet(null, t);
/*     */         } finally {
/* 159 */           this.completed.incrementAndGet();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\artem\Downloads\zakoopt-1.1.6+1.21.11 (1).jar!\zako\opt\particle\Workers.class
 * Java compiler version: 21 (65.0)
 * JD-Core Version:       1.1.3
 */