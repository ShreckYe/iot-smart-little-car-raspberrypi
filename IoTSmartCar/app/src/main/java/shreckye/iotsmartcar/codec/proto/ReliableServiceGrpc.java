package shreckye.iotsmartcar.codec.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: iot_smart_car.proto")
public final class ReliableServiceGrpc {

  private ReliableServiceGrpc() {}

  public static final String SERVICE_NAME = "iotsmartcar.codec.ReliableService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest,
      shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse> getCaptureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Capture",
      requestType = shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest.class,
      responseType = shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest,
      shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse> getCaptureMethod() {
    io.grpc.MethodDescriptor<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest, shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse> getCaptureMethod;
    if ((getCaptureMethod = ReliableServiceGrpc.getCaptureMethod) == null) {
      synchronized (ReliableServiceGrpc.class) {
        if ((getCaptureMethod = ReliableServiceGrpc.getCaptureMethod) == null) {
          ReliableServiceGrpc.getCaptureMethod = getCaptureMethod = 
              io.grpc.MethodDescriptor.<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest, shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "iotsmartcar.codec.ReliableService", "Capture"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new ReliableServiceMethodDescriptorSupplier("Capture"))
                  .build();
          }
        }
     }
     return getCaptureMethod;
  }

  private static volatile io.grpc.MethodDescriptor<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest,
      shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem> getGetPhotoListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPhotoList",
      requestType = shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest.class,
      responseType = shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest,
      shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem> getGetPhotoListMethod() {
    io.grpc.MethodDescriptor<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest, shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem> getGetPhotoListMethod;
    if ((getGetPhotoListMethod = ReliableServiceGrpc.getGetPhotoListMethod) == null) {
      synchronized (ReliableServiceGrpc.class) {
        if ((getGetPhotoListMethod = ReliableServiceGrpc.getGetPhotoListMethod) == null) {
          ReliableServiceGrpc.getGetPhotoListMethod = getGetPhotoListMethod = 
              io.grpc.MethodDescriptor.<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest, shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "iotsmartcar.codec.ReliableService", "GetPhotoList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem.getDefaultInstance()))
                  .setSchemaDescriptor(new ReliableServiceMethodDescriptorSupplier("GetPhotoList"))
                  .build();
          }
        }
     }
     return getGetPhotoListMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReliableServiceStub newStub(io.grpc.Channel channel) {
    return new ReliableServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReliableServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ReliableServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReliableServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ReliableServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ReliableServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void capture(shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest request,
        io.grpc.stub.StreamObserver<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCaptureMethod(), responseObserver);
    }

    /**
     */
    public void getPhotoList(shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest request,
        io.grpc.stub.StreamObserver<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPhotoListMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCaptureMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest,
                shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse>(
                  this, METHODID_CAPTURE)))
          .addMethod(
            getGetPhotoListMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest,
                shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem>(
                  this, METHODID_GET_PHOTO_LIST)))
          .build();
    }
  }

  /**
   */
  public static final class ReliableServiceStub extends io.grpc.stub.AbstractStub<ReliableServiceStub> {
    private ReliableServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReliableServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ReliableServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReliableServiceStub(channel, callOptions);
    }

    /**
     */
    public void capture(shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest request,
        io.grpc.stub.StreamObserver<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCaptureMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPhotoList(shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest request,
        io.grpc.stub.StreamObserver<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetPhotoListMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ReliableServiceBlockingStub extends io.grpc.stub.AbstractStub<ReliableServiceBlockingStub> {
    private ReliableServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReliableServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ReliableServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReliableServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse capture(shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest request) {
      return blockingUnaryCall(
          getChannel(), getCaptureMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem> getPhotoList(
        shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetPhotoListMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ReliableServiceFutureStub extends io.grpc.stub.AbstractStub<ReliableServiceFutureStub> {
    private ReliableServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ReliableServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ReliableServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ReliableServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse> capture(
        shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCaptureMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CAPTURE = 0;
  private static final int METHODID_GET_PHOTO_LIST = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ReliableServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ReliableServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CAPTURE:
          serviceImpl.capture((shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureRequest) request,
              (io.grpc.stub.StreamObserver<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.CaptureResponse>) responseObserver);
          break;
        case METHODID_GET_PHOTO_LIST:
          serviceImpl.getPhotoList((shreckye.iotsmartcar.codec.proto.IotSmartCarProto.GetPhotoListRequest) request,
              (io.grpc.stub.StreamObserver<shreckye.iotsmartcar.codec.proto.IotSmartCarProto.PhotoItem>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ReliableServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReliableServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return shreckye.iotsmartcar.codec.proto.IotSmartCarProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ReliableService");
    }
  }

  private static final class ReliableServiceFileDescriptorSupplier
      extends ReliableServiceBaseDescriptorSupplier {
    ReliableServiceFileDescriptorSupplier() {}
  }

  private static final class ReliableServiceMethodDescriptorSupplier
      extends ReliableServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReliableServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ReliableServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReliableServiceFileDescriptorSupplier())
              .addMethod(getCaptureMethod())
              .addMethod(getGetPhotoListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
